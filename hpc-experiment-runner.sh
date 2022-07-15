#!/bin/bash

#execution: ./experiment-runner.sh path_to_classes path_to_config project_path

# Prepare variables passed as environment to the hpc scheduler
export CLASSPATH_ADDITION=""
export CONFIG=""
export PROJECT_DIR=""
export BATCH_NUMBER=0

# Be aware of os specific path separators in classpath (: Linux, ; Windows/Mac)
execute_single_config () {
	sbatch --export=CLASSPATH_ADDITION,CONFIG,PROJECT_DIR,BATCH_NUMBER single-config-multi-batches-job.slurm
}

# Three parameters: classpath, configuration list, project dir, model (optional)
execute_all_configs () {
	for CONFIG in ${CONFIGS[@]}
	do
		[ -e "$CONFIG" ] || continue
		if [ ! -z "$model" ]; then
			sed -i 's/model <.*xmi>/model <'"$model"'>/g' "$CONFIG"
		fi
		echo "Executing $CONFIG"
		execute_single_config "$CLASSPATH_ADDITION" "$CONFIG" "$PROJECT_DIR"
	done
}

# Specify path to fitness function classes and meta model classes.
# As usual for java classpath all classes in subdirectories will be considered.
# 
CLASSPATH_ADDITION="$1"

# Specify a single configuration file or a directory containing configuration files.
# In case of a directory, configurations inside subdirectories will not be considered.
if [ "$2" -gt 0 ]; then
	BATCH_NUMBER="$2"
	echo "$BATCH_NUMBER batches will be performed"
else
	echo "Invalid batch number"
	exit 1
fi	

if [ -f "$2" ]; then
	echo "Single configuration file will be executed"
	CONFIGS="$2"
elif [ -d "$2" ]; then
	echo "All configs in the specified directory will be executed"
	CONFIGS=()
	for path in "$2"/*
	do
		if [ -f "$path" ]; then
			CONFIGS+=("$path")
		fi
	done
	for CONFIG in ${CONFIGS[@]}
	do
		echo $CONFIG
	done
else
	echo "Invalid configuration specification"
	exit 1
fi	

# The path relative to which the .mopt configurations will be interpreted.
# This means, the basepath inside .mopt configurations will consider this
# project directory as root directory.
if [ -d "$3" ]; then
	PROJECT_DIR="$3";
else
	echo "Invalid project directory"
	exit 1
fi

# As fourth param specify a file containing a list of models which should be optimized
# execute_all_configs() will adapt the currently run config automatically
if [ -f "$4" ]; then
	MODEL_LIST=$(cat "$4");
	for model in $MODEL_LIST
	do
		execute_all_configs "$CLASSPATH_ADDITION" "$CONFIGS" "$PROJECT_DIR" "$model"
	done
else
	execute_all_configs "$CLASSPATH_ADDITION" "$CONFIGS" "$PROJECT_DIR"
fi