#!/bin/bash

# Create users for admin DB and test DB.
mongo << EOF
use admin
db.createUser(
  {
    user: "root",
    pwd: "P@ssw0rd",
    roles: [{role: "root", db: "admin"}]
  }
)
db.createUser(
  {
    user: "admin",
    pwd: "P@ssw0rd",
    roles: [{role: "userAdminAnyDatabase", db: "admin"}]
  }
)

use test
db.createUser(
  {
    user: "tester",
    pwd: "P@ssw0rd",
    roles: [{role: "dbOwner", db: "test"}]
 }
)
EOF
