const Cache = require('../../repositories/cache.repository');
const { BLACKLIST_CACHE_PREFIX, ALGORITHM } = require('../confs');

const name = 'jwt';

const schema = 'jwt';

const secret = require('../../config');

const options = {
  key: secret,
  validate: async (decoded, h) => {
    const unlogged = await Cache.get(`${BLACKLIST_CACHE_PREFIX}${h.auth.token}`);
    return { isValid: !unlogged };
  },
  verifyOptions: { algorithms: [ALGORITHM] },
};

module.exports = {
  name,
  schema,
  options,
};
