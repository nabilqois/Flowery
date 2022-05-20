const Tutorial =  require("../models/Tutorial");

const addTutorialHandler = async(request, h) => {
  try {
      var newTutorial = new Tutorial(request.payload);
      var result = await newTutorial.save();
      return h.response(result);
  } catch (error) {
      console.log(error)
      return h.response({message: error.message}).code(500);
  }
};

const getAllTutorialsHandler = async(request, h) => {
  try {
      var tutorials = await Tutorial.find().exec();
      return h.response(tutorials);
  } catch (error) {
      console.log(error)
      return h.response(error).code(500);
  }
};

const getTutorialByIdHandler = async (request, h) => {
  try {
      var tutorial = await Tutorial.findById(request.params.id).exec();
      return h.response(tutorial);
  } catch (error) {
      return h.response({message: error.message}).code(500);
  }
}

const editTutorialByIdHandler = async (request, h) => {
  try {
    var tutorial = await Tutorial.findByIdAndUpdate(request.params.id, request.payload, { new: true });
    return h.response(tutorial);
  } catch (error) {
    return h.response(error).code(500);
  }
}
const deleteTutorialByIdHandler = async (request, h) => {
  try {
    var tutorial = await Tutorial.findByIdAndDelete(request.params.id);
    return h.response(tutorial);
  } catch (error) {
    return h.response(error).code(500);
  }
}

module.exports = {
  addTutorialHandler,
  getAllTutorialsHandler,
  getTutorialByIdHandler,
  editTutorialByIdHandler,
  deleteTutorialByIdHandler,
};
  