#!/bin/bash
# Generate a zip file excluding annotation-tools.zip
# checkers-1.3.0.zip and jsr308-langtools-1.3.0.zip
# Author: Wei Huang
# Date: July 22, 2012

#ls -1 | grep -v '.*zip' | xargs zip -r type-inference.zip 
hg archive type-inference.zip
