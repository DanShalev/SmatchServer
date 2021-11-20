#Welcome to SmatchServer


In order to run the application you will need the following installed:

* Maven
* Mysql
* Lombok


##Initiate Mysql:

connect as a root:
>$ sudo mysql --password

create tie_db:
>$ create database tie_db;

create user for the spring app:
>$ create user 'springuser'@'%' identified by 'ThePassword';

grant permission for that user:
>$ grant all on tie_db.* to 'springuser'@'%';

Connect to db:
>$ mysql --user="springuser" --password="ThePassword" --database="tie_db"

run tie_db_schema.sql (or path to the file, if your'e not in the directory):
>$ source tie_db_scheme.sql
> 
#

##Connect to EC2 vis SSH

run the following commands via shell:
>chmod 400 <BASE_PATH>/tieserver/src/main/resources/aws_certificate.cer

>ssh -i <BASE_PATH>/tieserver/src/main/resources/aws_certificate.cer ec2-user@ec2-18-188-126-103.us-east-2.compute.amazonaws.com

In the VM, the jar files are located at
>/home/workspace/com/smatchServer/0.0.1-SNAPSHOT

If you want to kill the current spring boot application, run
>ps aux | grep ".jar" | grep -v "grep" | awk '{print $2}'
#

##Connect to RDS

host:
>db-smatch.csqpa04jgsqn.us-east-2.rds.amazonaws.com

port:
>3360

user and password are mentioned under mysql

#

##Deploy to EC2 with maven

The deployment process will create a new jar version on EC2. In order to deploy run:
>clean deploy -DskipTests=true

In order to kill the current process run:

>kill -9 ${ps aux | grep ".jar" | grep -v "grep" | awk '{print $2}'}




