#!/bin/bash
# This script is used for copying necessary files from the working copy
# to the current Google Project Repository. 
# Author: Wei Huang <csweihuang@gmail.com> 
# Date: July 21, 2012


working_dir='/Users/huangw5/Projects/workspace-latest-checker/checker-framework/checkers/'

# 1. Copy the annotated JDK
echo -n "Copying the annotated JDK..."
JDK='jdk'
if [ ! -d $JDK ]; then
  mkdir $JDK
fi

cp $working_dir"/"$JDK/Makefile ./$JDK/
cp $working_dir"/"$JDK/jdk.jar ./$JDK/
cp -r $working_dir"/"$JDK/reim ./$JDK/

echo "Done."

# 2. Copy source
echo -n "Copying source files..."
src_dir='src/checkers/inference/'
if [ ! -d $src_dir ]; then
  mkdir -p $src_dir
fi

cp -r $working_dir"/"$src_dir"" $src_dir

echo "Done."

# 3. Copy test cases
echo -n "Copying test cases..."
test_dir='inference-tests/'
if [ ! -d $test_dir ]; then
  mkdir -p $test_dir
fi

cp -r $working_dir"/"$test_dir"" $test_dir

echo "Done."

# 4. Copy binary
echo -n "Copying binary..."
bin_dir='binary/' 
if [ ! -d $bin_dir ]; then
  mkdir -p $bin_dir
fi

cp -r $working_dir"/"$bin_dir"" $bin_dir

echo "Done."

# 5. Copy jar files and other csv files
echo -n "Copying jar files and csv files..."
cp $working_dir/checkers*.jar ./
cp $working_dir/lib-*.csv ./
echo "Done."

# 6. Copy benchmarks
echo "Copying benchmarks..."
src_benchdir='/Users/huangw5/Projects/proganalysis/projects/benchmarks/'
benchdir='benchmarks'

if [ ! -d $benchdir ]; then
  mkdir -p $benchdir
fi

# Enter the dir
cd $benchdir

# SPECjbb is not open source? 
for bench in jolden tinySQL htmlparser eclipsec xalan-j_2_7_1 \
  javad commons-pool-1.2 jdbm-1.0 jdbf-0.1.1 jtds-1.0 
do
  echo -n "Exporting benchmark $bench..."
  svn export --force $src_benchdir/$bench
done

cd ..

# 7. Copy benchmark scripts
echo -n "Copying running scripts..."

for script_dir in 2012-oopsla-eval
do 
  if [ ! -d $script_dir ]; then
    mkdir -p $script_dir
  fi
  cp $working_dir/$script_dir/*.sh ./$script_dir
done

echo "Done."
