const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');
const { ERR_DUPLICATE_EMAIL } = require('../utils/errorTypes');

const usermodel = mongoose.Schema({
    name: {
        type: String,
        required: "Name is required",
    }, 
    email: {
        type: String,
        required: "Email is required",
        unique: true,
    },
    password: {
        type: String,
        required: "Password is required",
    } 
}, {
    timestamps: true
});

// encrypting password before saving
usermodel.pre('save', async function(next){
    if(!this.isModified('password')){
        next()
    }
    this.password = await bcrypt.hash(this.password, 10);
 });

usermodel.post('save', function(error, doc, next) {
    if (error.name === 'MongoServerError' && error.code === 11000) {
        next(new Error(ERR_DUPLICATE_EMAIL));
    } else {
        next(error);
    }
});

usermodel.post('findOneAndUpdate', function(error, doc, next) {
    if (error.name === 'MongoServerError' && error.code === 11000) {
        next(new Error(ERR_DUPLICATE_EMAIL));
    } else {
        next(error);
    }
});
module.exports = mongoose.model('User', usermodel);