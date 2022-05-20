// 'use strict';

const Hapi = require('@hapi/hapi');
const routes = require('./routes');

const hapiAuthJwt2 = require('hapi-auth-jwt2');
const jwtStrategy = require('./auth/strategies/JWT');

require('dotenv').config();
require('./database');
const init = async () => {
  const server = Hapi.server({
    port: 5000,
    host: process.env.NODE_ENV !== 'production' ? 'localhost' : '0.0.0.0',
    routes: {
      cors: {
        origin: ['*'],
      },
    },
  });
  
  server.route(routes);

  const initializePlugins = async () => {
    await server.register(hapiAuthJwt2);
  
    // Defining authentication strategy
    server.auth.strategy(jwtStrategy.name, jwtStrategy.schema, jwtStrategy.options);
    server.auth.default(jwtStrategy.name);
  };

  await initializePlugins();
  await server.start();
  console.log(`Server berjalan pada ${server.info.uri}`);
};

process.on('unhandledRejection', (err) => {
  console.log(err);
  process.exit(1);
});
init();
