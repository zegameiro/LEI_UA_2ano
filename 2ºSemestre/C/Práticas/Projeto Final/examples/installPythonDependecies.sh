#!/bin/bash

advClassesPath=$(realpath ../src/)
echo "Adding advClasses to PYTHONPATH on ./.profile ."
echo -e "\nexport PYTHONPATH=""$""PYTHONPATH"":"$advClassesPath >> ~/.profile

echo "Installing numpy."
pip install numpy

echo "Installing opencv."
pip install opencv-python

echo "Installing antlr4-runtime"
pip install antlr4-python3-runtime

echo "Installation finished, restart terminal might be required."
