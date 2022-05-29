const Flower =  require("../models/Flower");

const addFlowerHandler = async(request, h) => {
  try {
      var newFlower = new Flower(request.payload);
      var result = await newFlower.save();
      return h.response({
        error: false,
        message: "Flower Created",
        result: result
      });
  } catch (error) {
      console.log(error)
      return h.response({
        error: true,
        message: error.message
      }).code(500);
  }
};

const getAllFlowersHandler = async(request, h) => {
  try {
      var flowers = await Flower.find().exec();
      return h.response({
        error: false,
        message: "success",
        result: flowers
      });
  } catch (error) {
      console.log(error)
      return h.response({
        error: true,
        message: error.message
      }).code(500);
  }
};

const getFlowerByIdHandler = async (request, h) => {
  try {
      var flower = await Flower.findById(request.params.id).exec();
      return h.response({
        error: false,
        message: "success",
        result: flower
      });
  } catch (error) {
      return h.response({
        error: true,
        message: error.message
      }).code(500);
  }
}

const editFlowerByIdHandler = async (request, h) => {
  try {
    var flower = await Flower.findByIdAndUpdate(request.params.id, request.payload, { new: true });
    return h.response({
      error: false,
      message: "Flower Updated",
      result: flower
    });
  } catch (error) {
    return h.response({
      error: true,
      message: error.message
    }).code(500);
  }
}
const deleteFlowerByIdHandler = async (request, h) => {
  try {
    var flower = await Flower.findByIdAndDelete(request.params.id);
    return h.response({
      error: false,
      message: "Flower Deleted",
      result: flower
    });
  } catch (error) {
    return h.response({
      error: true,
      message: error.message
    }).code(500);
  }
}

module.exports = {
  addFlowerHandler,
  getAllFlowersHandler,
  getFlowerByIdHandler,
  editFlowerByIdHandler,
  deleteFlowerByIdHandler,
};
  