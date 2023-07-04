package com.example.currencytask.models

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement

@JacksonXmlRootElement(localName = "ValCurs")
data class ValCurs(
    @JacksonXmlProperty(isAttribute = true, localName = "ID")
    var id: String? = null,

    @JacksonXmlProperty(isAttribute = true, localName = "DateRange1")
    var dateRange1: String? = null,

    @JacksonXmlProperty(isAttribute = true, localName = "DateRange2")
    var dateRange2: String? = null,

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    var name: String? = null,

    @JacksonXmlProperty(localName = "Record")
    @JacksonXmlElementWrapper(useWrapping = false)
    var records: List<Currency>? = null
)
