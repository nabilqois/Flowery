const { 
  addFlowerHandler,
  getAllFlowersHandler,
  getFlowerByIdHandler,
  editFlowerByIdHandler,
  deleteFlowerByIdHandler,
 } = require('./controllers/FlowersController')

const routes = [
  {
    method: 'GET',
    path: '/flowers',
    handler: getAllFlowersHandler,
  },
  {
    method: 'POST',
    path: '/flowers',
    handler: addFlowerHandler,
  },
  {
    method: 'GET',
    path: '/flowers/{id}',
    handler: getFlowerByIdHandler,
  },
  {
    method: 'PUT',
    path: '/flowers/{id}',
    handler: editFlowerByIdHandler,
  },
  {
    method: 'DELETE',
    path: '/flowers/{id}',
    handler: deleteFlowerByIdHandler,
  },
];
module.exports = routes;
