const { 
  addArticleHandler,
  getAllArticlesHandler,
  getArticleByIdHandler,
  editArticleByIdHandler,
  deleteArticleByIdHandler,
 } = require('./controllers/ArticlesController')

 const { 
  addFavouriteHandler,
  getAllFavouritesHandler,
  getFavouriteByIdHandler,
  editFavouriteByIdHandler,
  deleteFavouriteByIdHandler,
 } = require('./controllers/FavouritesController')

const { 
  addFlowerHandler,
  getAllFlowersHandler,
  getFlowerByIdHandler,
  editFlowerByIdHandler,
  deleteFlowerByIdHandler,
 } = require('./controllers/FlowersController')

 const { 
  addTriviaHandler,
  getAllTriviasHandler,
  getTriviaByIdHandler,
  editTriviaByIdHandler,
  deleteTriviaByIdHandler,
 } = require('./controllers/TriviasController')

 const { 
  addTutorialHandler,
  getAllTutorialsHandler,
  getTutorialByIdHandler,
  editTutorialByIdHandler,
  deleteTutorialByIdHandler,
 } = require('./controllers/TutorialsController')

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
    path: '/articles',
    handler: getAllArticlesHandler,
  },
  {
    method: 'GET',
    path: '/favourites',
    handler: getAllFavouritesHandler,
  },
  {
    method: 'GET',
    path: '/flowers',
    handler: getAllFlowersHandler,
  },
  {
    method: 'GET',
    path: '/trivias',
    handler: getAllTriviasHandler,
  },
  {
    method: 'GET',
    path: '/tutorials',
    handler: getAllTutorialsHandler,
  },
  {
    method: 'GET',
    path: '/users',
    handler: getAllUsersHandler,
  },
  {
    method: 'POST',
    path: '/articles',
    handler: addArticleHandler,
  },
{
    method: 'POST',
    path: '/favourites',
    handler: addFavouriteHandler,
  },
  {
    method: 'POST',
    path: '/flowers',
    handler: addFlowerHandler,
  },
  {
    method: 'POST',
    path: '/trivias',
    handler: addTriviaHandler,
  },
{
    method: 'POST',
    path: '/tutorials',
    handler: addTutorialHandler,
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
    path: '/articles/{id}',
    handler: getArticleByIdHandler,
  },
{
    method: 'GET',
    path: '/favourites/{id}',
    handler: getFavouriteByIdHandler,
  },
  {
    method: 'GET',
    path: '/flowers/{id}',
    handler: getFlowerByIdHandler,
  },
  {
    method: 'GET',
    path: '/trivias/{id}',
    handler: getTriviaByIdHandler,
  },
{
    method: 'GET',
    path: '/tutorials/{id}',
    handler: getTutorialByIdHandler,
  },
  {
    method: 'GET',
    path: '/users/{id}',
    handler: getUserByIdHandler,
  },
  {
    method: 'PUT',
    path: '/articles/{id}',
    handler: editArticleByIdHandler,
  },
{
    method: 'PUT',
    path: '/favourites/{id}',
    handler: editFavouriteByIdHandler,
  },
  {
    method: 'PUT',
    path: '/flowers/{id}',
    handler: editFlowerByIdHandler,
  },
  {
    method: 'PUT',
    path: '/trivias/{id}',
    handler: editTriviaByIdHandler,
  },
{
    method: 'PUT',
    path: '/tutorials/{id}',
    handler: editTutorialByIdHandler,
  },
  {
    method: 'PUT',
    path: '/users/{id}',
    handler: editUserByIdHandler,
  },
  {
    method: 'DELETE',
    path: '/articles/{id}',
    handler: deleteArticleByIdHandler,
  },
{
    method: 'DELETE',
    path: '/favourites/{id}',
    handler: deleteFavouriteByIdHandler,
  },
  {
    method: 'DELETE',
    path: '/flowers/{id}',
    handler: deleteFlowerByIdHandler,
  },
  {
    method: 'DELETE',
    path: '/trivias/{id}',
    handler: deleteTriviaByIdHandler,
  },
{
    method: 'DELETE',
    path: '/tutorials/{id}',
    handler: deleteTutorialByIdHandler,
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
