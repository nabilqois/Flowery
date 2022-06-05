const UserModel = require('../models/User');
const Cache = require('./cache.repository');
const { LOGIN_EXPIRATION_TIME } = require('../auth/confs');

const PREFIX_CACHE = 'userId:';

const findByEmail = email => (
  UserModel.findOne({ email })
);

const setCache = user => (
  Cache.set(`${PREFIX_CACHE}${user.id}`, JSON.stringify(user), LOGIN_EXPIRATION_TIME)
);

const removeCache = userId => (
  Cache.del(`${PREFIX_CACHE}${userId}`)
);

module.exports = {
  findByEmail,
  setCache,
  removeCache,
};