cmake_minimum_required(VERSION 3.2)
project(PA5_2)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    Dictionary.c
    Dictionary.h
    DictionaryClient.c)

add_executable(PA5_2 ${SOURCE_FILES})