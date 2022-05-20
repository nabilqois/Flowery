const { 
  addFlowerHandler,
  getAllFlowersHandler,
  getFlowerByIdHandler,
  editFlowerByIdHandler,
  deleteFlowerByIdHandler,
 } = require('./controllers/FlowersController')

 const { 
  addUserHandler,
  getAllUsersHandler,
  getUserByIdHandler,
  editUserByIdHandler,
  deleteUserByIdHandler,
 } = require('./controllers/UsersController')

 const {
   loginHandler,
   logoutHandler,
 } = require('./controllers/AuthController')

const routes = [
  {
    method: 'GET',
    path: '/flowers',
    handler: getAllFlowersHandler,
  },
  {
    method: 'GET',
    path: '/users',
    handler: getAllUsersHandler,
  },
  {
    method: 'POST',
    path: '/flowers',
    handler: addFlowerHandler,
  },
  {
    method: 'POST',
    path: '/users',
    handler: addUserHandler,
    options: {
      auth: false,
    },
  },
  {
    method: 'GET',
    path: '/flowers/{id}',
    handler: getFlowerByIdHandler,
  },
  {
    method: 'GET',
    path: '/users/{id}',
    handler: getUserByIdHandler,
  },
  {
    method: 'PUT',
    path: '/flowers/{id}',
    handler: editFlowerByIdHandler,
  },
  {
    method: 'PUT',
    path: '/users/{id}',
    handler: editUserByIdHandler,
  },
  {
    method: 'DELETE',
    path: '/flowers/{id}',
    handler: deleteFlowerByIdHandler,
  },
  {
    method: 'DELETE',
    path: '/users/{id}',
    handler: deleteUserByIdHandler,
  },
  {
    method: 'POST',
    path: '/login',
    handler: loginHandler,
    options: {
      auth: false,
    },
  },
  {
    method: 'POST',
    path: '/logout',
    handler: logoutHandler,
  },
];
module.exports = routes;
