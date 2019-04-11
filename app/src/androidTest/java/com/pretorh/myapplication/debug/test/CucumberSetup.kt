package com.pretorh.myapplication.debug.test

import cucumber.api.CucumberOptions

@CucumberOptions(
    features = ["features"],
    glue = ["com.pretorh.myapplication.glue"]
)
class CucumberSetup
