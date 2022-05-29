const mongoose = require('mongoose');

mongoose.connect('mongodb://0.0.0.0/db_flowery', {
  useUnifiedTopology: true,
  useNewUrlParser: true,
})
  .then(() => console.log('Database is Connected'))
  .catch(err => console.log(err));