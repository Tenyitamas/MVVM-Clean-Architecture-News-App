package com.tenyitamas.kip_knowledgeispower.domain.use_case

data class NewsUseCases(
    val deleteArticle: DeleteArticle,
    val getSavedArticle: GetSavedArticle,
    val getTopNews: GetTopNews,
    val saveArticle: SaveArticle,
    val searchNews: SearchNews
)
