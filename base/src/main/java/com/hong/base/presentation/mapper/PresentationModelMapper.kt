package com.hong.base.presentation.mapper

import com.hong.base.domain.model.Entity
import com.hong.base.presentation.model.PresentationModel

interface PresentationModelMapper<in E : Entity, out P : PresentationModel> {
    fun toPresentationModel(entity: E): P
}
