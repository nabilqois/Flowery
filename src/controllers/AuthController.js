const boom = require('boom');
const authenticate = require('../auth/authenticate.auth');
const userRepository = require('../repositories/users.repository');

const {
    ERR_INVALID_PASSWORD,
    ERR_INVALID_TOKEN,
    ERR_USER_NOT_FOUND,
} = require('../utils/errorTypes');
  
const loginHandler = async (req, h) => {
    const { email, password } = req.payload;

    try {
        const { user, token } = await authenticate.login(email, password);

        await userRepository.setCache(user);

        return h.response({
            error: false,
            message: "Success Logged In",
            result: {
                user_id: user._id,
                user_name: user.name,
                token: token
            }
        }).code(200);
    } catch (e) {
        switch (e.message) {
        case ERR_INVALID_PASSWORD:
            throw boom.notFound('Invalid password');
        case ERR_INVALID_TOKEN:
            throw boom.badImplementation('Error generating token');
        case ERR_USER_NOT_FOUND:
            throw boom.notFound('E-mail not found');
        default:
            throw boom.badImplementation(e);
        }
    }
};

const logoutHandler = async (req, h) => {
    const { credentials, token } = req.auth;
    try {
        await Promise.all([
            authenticate.logout(token),
            userRepository.removeCache(credentials.data.user_id),
        ]);

        return h.response({
            error: false,
            message: "You just logged out the app"
        }).code(200);
    } catch (e) {
        console.error(e);
        throw boom.badImplementation();
    }
};

module.exports = {
    loginHandler,
    logoutHandler
};