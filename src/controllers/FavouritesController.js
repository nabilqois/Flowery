const Favourite =  require("../models/Favourite");

const addFavouriteHandler = async(request, h) => {
  try {
      var newFavourite = new Favourite(request.payload);
      var result = await newFavourite.save();
      return h.response(result);
  } catch (error) {
      console.log(error)
      return h.response({message: error.message}).code(500);
  }
};

const getAllFavouritesHandler = async(request, h) => {
  try {
      var favourites = await Favourite.find().exec();
      return h.response(favourites);
  } catch (error) {
      console.log(error)
      return h.response(error).code(500);
  }
};

const getFavouriteByIdHandler = async (request, h) => {
  try {
      var favourite = await Favourite.findById(request.params.id).exec();
      return h.response(favourite);
  } catch (error) {
      return h.response({message: error.message}).code(500);
  }
}

const editFavouriteByIdHandler = async (request, h) => {
  try {
    var favourite = await Favourite.findByIdAndUpdate(request.params.id, request.payload, { new: true });
    return h.response(favourite);
  } catch (error) {
    return h.response(error).code(500);
  }
}
const deleteFavouriteByIdHandler = async (request, h) => {
  try {
    var favourite = await Favourite.findByIdAndDelete(request.params.id);
    return h.response(favourite);
  } catch (error) {
    return h.response(error).code(500);
  }
}

module.exports = {
  addFavouriteHandler,
  getAllFavouritesHandler,
  getFavouriteByIdHandler,
  editFavouriteByIdHandler,
  deleteFavouriteByIdHandler,
};
  