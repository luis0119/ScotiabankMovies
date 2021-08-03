package com.example.application.exception

import com.example.utilities.Constants.NOT_FOUND_CATEGORY_MESSAGE

class CategoryNotFound : RuntimeException(NOT_FOUND_CATEGORY_MESSAGE)