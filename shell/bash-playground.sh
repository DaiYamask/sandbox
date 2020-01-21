#!/bin/bash

# how to use variable 
## set value to variable
a=1234
echo "The value of \"a\" is $a."

# If the variable is not empty
if [ -n "$a" ]; then
  echo "var is not empty"
  a=13+23
fi
echo "The value of \"a\" is $a. It's updated."

# If the variable is emtpy or not defined
if [ -z "$b" ]; then
  echo "var is empty"
fi
