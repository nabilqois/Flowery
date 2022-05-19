const Article =  require("../models/Article");

const addArticleHandler = async(request, h) => {
  try {
      var newArticle = new Article(request.payload);
      var result = await newArticle.save();
      return h.response(result);
  } catch (error) {
      console.log(error)
      return h.response({message: error.message}).code(500);
  }
};

const getAllArticlesHandler = async(request, h) => {
  try {
      var articles = await Article.find().exec();
      return h.response(articles);
  } catch (error) {
      console.log(error)
      return h.response(error).code(500);
  }
};

const getArticleByIdHandler = async (request, h) => {
  try {
      var article = await Article.findById(request.params.id).exec();
      return h.response(article);
  } catch (error) {
      return h.response({message: error.message}).code(500);
  }
}

const editArticleByIdHandler = async (request, h) => {
  try {
    var article = await Article.findByIdAndUpdate(request.params.id, request.payload, { new: true });
    return h.response(article);
  } catch (error) {
    return h.response(error).code(500);
  }
}
const deleteArticleByIdHandler = async (request, h) => {
  try {
    var article = await Article.findByIdAndDelete(request.params.id);
    return h.response(article);
  } catch (error) {
    return h.response(error).code(500);
  }
}

module.exports = {
  addArticleHandler,
  getAllArticlesHandler,
  getArticleByIdHandler,
  editArticleByIdHandler,
  deleteArticleByIdHandler,
};
  