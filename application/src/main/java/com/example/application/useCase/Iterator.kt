package com.example.application.useCase

interface Iterator <out Results,in Params> {

   suspend fun execute(params: Params) : Results
}