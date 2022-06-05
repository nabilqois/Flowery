const User =  require("../models/User");

const hash = require('../utils/hash');
const { ERR_INVALID_PASSWORD } = require('../utils/errorTypes');

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
    var id = request.params.id;
    var oldPassword = request.payload.password;
    // var hashOldPassword = await hash.make(request.payload.password);
    var hashNewPassword = await hash.make(request.payload.new_password);
    
    if (hashNewPassword) {
      var userAccount = await User.findById( id );
      const passwordOk = await hash.compare(oldPassword, userAccount.password);
      if (!passwordOk) {
          throw new Error(ERR_INVALID_PASSWORD);
      }
      const changePassword = {name: request.payload.name, email: request.payload.email, password: hashNewPassword};
      var user = await User.findByIdAndUpdate(request.params.id, changePassword, { new: true });
      return h.response({
        error: false,
        message: "User Updated",
        result: user
      });
    }
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
  