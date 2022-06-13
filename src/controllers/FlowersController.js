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
      var flowers = await Flower.aggregate([
        {
            $lookup:
            {
                from: "tutorials",
                localField: "_id",
                foreignField: "flower_id",
                as: "tutorials"
            }
        }
      ]).exec();
      const { kueri } = request.query;
      if (kueri) {
        const lokal = flowers.filter((f) => f.local_name.toLowerCase().includes(kueri.toLowerCase()));
        const global = flowers.filter((f) => f.global_name.toLowerCase().includes(kueri.toLowerCase()));
        const ilmiah = flowers.filter((f) => f.scientific_name.toLowerCase().includes(kueri.toLowerCase()));
        if (lokal.length > 0) {
          return h.response({
            error: false,
            message: "success",
            result: lokal
          });
        } else if (global.length > 0) {
          return h.response({
            error: false,
            message: "success",
            result: global
          });
        } else if (ilmiah.length > 0) {
          return h.response({
            error: false,
            message: "success",
            result: ilmiah
          });
        } else {
          return h.response({
            error: false,
            message: "Flower Not Found"
          });
        }
      }
      if (!kueri) {  
        return h.response({
          error: false,
          message: "success",
          result: flowers.sort(function (a, b) {return Math.random() - 0.5;})
        });
      }
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
      var flowers = await Flower.aggregate([
        {
            $lookup:
            {
                from: "tutorials",
                localField: "_id",
                foreignField: "flower_id",
                as: "tutorials"
            }
        }
      ]);
      const flower = flowers.filter((f) => f._id==request.params.id);
      const flowerObject = flower.map((f)=>({
        id: f._id,
        global_name: f.global_name,
        scientific_name: f.scientific_name,
        local_name: f.local_name,
        reference: f.reference,
        images: f.images,
        plant: f.tutorials.map(item => item.plant),
        take_care: f.tutorials.map(item => item.take_care)
      }));
      if (!flower) {
        return h.response({
          error: true,
          message: "FLower Not Found"
        });  
      }
      return h.response({
        error: false,
        message: "success",
        result: flowerObject
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
  