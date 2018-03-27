# Terraform version
terraform {
  required_version = "> 0.8.0"
}

# Provider and access details
provider "aws" {
 
  region     = "${var.aws_region}"
}

module "sonarqube_security_groups" {
  source               = "./securitygroups"
  vpc_id               = "${var.vpc_id}"
  security_subnet_cidr = ["${var.security_subnet_cidr}"]
}

# Create Sonarqube
module "sonarqube" {
  source             = "./instances"
  private_subnet_id  = "${var.private_subnet_id}"
  security_group_ids = ["${module.sonarqube_security_groups.sonarqube_sg_id}"]
  chef_server_url    = "${var.chef_server_url}"
  chef_run_list      = ["role[sonarqube]"]
}

