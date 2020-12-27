#!/bin/bash


echo -------create table--------

mysql --defaults-extra-file=root.conf < createTable.sql


echo ------- finish--------

echo -------create table--------


mysql --defaults-extra-file=root.conf --enable-local-infile < ./insert_product.sql

echo ------- finish--------

