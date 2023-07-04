package com.example.currencytask.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "Record")
data class Currency(
    @JacksonXmlProperty(isAttribute = true, localName = "Id")
    var id: String? = null,

    @JacksonXmlProperty(isAttribute = true, localName = "Date")
    var date: String? = null,

    @JacksonXmlProperty(localName = "Nominal")
    var nominal: Int? = null,

    @JacksonXmlProperty(localName = "Value")
    var value: String? = null
)
