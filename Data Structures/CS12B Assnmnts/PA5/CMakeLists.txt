cmake_minimum_required(VERSION 3.2)
project(PA5)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    Dictionary.c
    Dictionary.h
    DictionaryTest.c)

add_executable(PA5 ${SOURCE_FILES})