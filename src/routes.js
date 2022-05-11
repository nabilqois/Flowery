const {
  editFlowerByIdHandler,
  deleteFlowerByIdHandler,
} = require('./handler');

const routes = [
  {
    method: 'PUT',
    path: '/books/{id}',
    handler: editFlowerByIdHandler,
  },
  {
    method: 'DELETE',
    path: '/books/{id}',
    handler: deleteFlowerByIdHandler,
  },
];
module.exports = routes;
