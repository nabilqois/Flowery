const mongoose = require('mongoose');
const bcrypt = require('bcryptjs');

const usermodel = mongoose.Schema({
    name: {
        type: String,
        required: "Name is required",
    }, 
    email: {
        type: String,
        required: "Email is required",
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

module.exports = mongoose.model('User', usermodel);