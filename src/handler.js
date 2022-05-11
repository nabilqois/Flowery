const flowers = require('./flowers');

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
  editFlowerByIdHandler,
  deleteFlowerByIdHandler,
};
