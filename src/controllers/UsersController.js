const User =  require("../models/User");

const addUserHandler = async(request, h) => {
  try {
      var newUser = new User(request.payload);
      var result = await newUser.save();
      return h.response({
        error: false,
        message: "User Created",
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

const getAllUsersHandler = async(request, h) => {
  try {
      var users = await User.find().exec();
      return h.response({
        error: false,
        message: "success",
        result: users
      });
  } catch (error) {
      console.log(error)
      return h.response({
        error: true,
        message: error.message
      }).code(500);
  }
};

const getUserByIdHandler = async (request, h) => {
  try {
      var user = await User.findById(request.params.id).exec();
      return h.response({
        error: false,
        message: "success",
        result: user
      });
  } catch (error) {
      return h.response({
        error: true,
        message: error.message
      }).code(500);
  }
}

const editUserByIdHandler = async (request, h) => {
  try {
    var user = await User.findByIdAndUpdate(request.params.id, request.payload, { new: true });
    return h.response({
      error: false,
      message: "User Updated",
      result: user
    });
  } catch (error) {
    return h.response({
      error: true,
      message: error.message
    }).code(500);
  }
}
const deleteUserByIdHandler = async (request, h) => {
  try {
    var user = await User.findByIdAndDelete(request.params.id);
    return h.response({
      error: false,
      message: "User Deleted",
      result: user
    });
  } catch (error) {
    return h.response({
      error: true,
      message: error.message
    }).code(500);
  }
}

module.exports = {
  addUserHandler,
  getAllUsersHandler,
  getUserByIdHandler,
  editUserByIdHandler,
  deleteUserByIdHandler,
};
  