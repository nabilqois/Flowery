const { nanoid } = require('nanoid');
const flowers = require('./flowers');

const addFlowerHandler = (request, h) => {
  const { 
    globalName, localName, scientificName, howToTend, otherUsage,reference  
  } = request.payload;

  const id = nanoid(16);
  const createdAt = new Date().toISOString();
  const updatedAt = createdAt;

  const newFlower = {
    globalName, localName, scientificName, howToTend, otherUsage, reference, id, createdAt, updatedAt
  };

  flowers.push(newFlower);
  
  const isSuccess = flowers.filter((flower) => flower.id === id).length > 0;

  if (isSuccess) {
    const response = h.response({
      status: 'success',
      message: 'Bunga berhasil ditambahkan',
      data: {
        flowerId: id,
      },
    });
    response.code(201);
    return response;
  }

  const response = h.response({
    status: 'fail',
    message: 'Bunga berhasil ditambahkan',
  });
  response.code(500);
  return response;
};

const getAllFlowersHandler = () => ({
  status: 'success',
  data: {
    flowers,
  }
});

const getFlowerByIdHandler = (request, h) => {
  const { id } = request.params;

  const flower = flowers.filter((f) => f.id === id)[0];

  if (flower !== undefined) {
    return {
      status: 'success',
      data: {
        flower,
      },
    };
  }

  const response = h.response({
    status: 'fail',
    message: 'Bunga tidak ditemukan',
  });
  response.code(404);
  return response;
}

const editFlowerByIdHandler = (request, h) => {
  const { id } = request.params;
  const {
    globalName, localName, scientificName, howToTend, otherUsage, reference,
  } = request.payload;
  const updatedAt = new Date().toISOString();
  const index = flowers.findIndex((flower) => flower.id === id);
  if (index !== -1) {
    flowers[index] = {
      ...flowers[index],
      globalName,
      localName,
      scientificName,
      howToTend,
      otherUsage,
      reference,
      updatedAt,
    };
    const response = h.response({
      status: 'success',
      message: 'Bunga berhasil diperbarui',
    });
    response.code(200);
    return response;
  }
  const response = h.response({
    status: 'fail',
    message: 'Gagal memperbarui bunga. Id tidak ditemukan',
  });
  response.code(404);
  return response;
};

const deleteFlowerByIdHandler = (request, h) => {
  const { id } = request.params;
  const index = flowers.findIndex((flower) => flower.id === id);
  if (index !== -1) {
    flowers.splice(index, 1);
    const response = h.response({
      status: 'success',
      message: 'Bunga berhasil dihapus',
    });
    response.code(200);
    return response;
  }
  const response = h.response({
    status: 'fail',
    message: 'Bunga gagal dihapus. Id tidak ditemukan',
  });
  response.code(404);
  return response;
};

module.exports = {
  addFlowerHandler,
  getAllFlowersHandler,
  getFlowerByIdHandler,
  editFlowerByIdHandler,
  deleteFlowerByIdHandler,
};
