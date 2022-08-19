package com.hong.base.domain.mapper

import com.hong.base.data.model.DataModel
import com.hong.base.domain.model.Entity

interface EntityMapper<in D : DataModel, out E : Entity> {
    fun toEntity(dataModel: D): E
}
