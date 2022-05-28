const Trivia =  require("../models/Trivia");

const addTriviaHandler = async(request, h) => {
  try {
      var newTrivia = new Trivia(request.payload);
      var result = await newTrivia.save();
      return h.response(result);
  } catch (error) {
      console.log(error)
      return h.response({message: error.message}).code(500);
  }
};

const getAllTriviasHandler = async(request, h) => {
  try {
      var trivias = await Trivia.find().exec();
      return h.response(trivias);
  } catch (error) {
      console.log(error)
      return h.response(error).code(500);
  }
};

const getTriviaByIdHandler = async (request, h) => {
  try {
      var trivia = await Trivia.findById(request.params.id).exec();
      return h.response(trivia);
  } catch (error) {
      return h.response({message: error.message}).code(500);
  }
}

const editTriviaByIdHandler = async (request, h) => {
  try {
    var trivia = await Trivia.findByIdAndUpdate(request.params.id, request.payload, { new: true });
    return h.response(trivia);
  } catch (error) {
    return h.response(error).code(500);
  }
}
const deleteTriviaByIdHandler = async (request, h) => {
  try {
    var trivia = await Trivia.findByIdAndDelete(request.params.id);
    return h.response(trivia);
  } catch (error) {
    return h.response(error).code(500);
  }
}

module.exports = {
  addTriviaHandler,
  getAllTriviasHandler,
  getTriviaByIdHandler,
  editTriviaByIdHandler,
  deleteTriviaByIdHandler,
};
  