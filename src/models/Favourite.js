const mongoose = require('mongoose');

const favouritesmodel = mongoose.Schema({
    user_id: {
        type: String,
        required: "User Id is required",
    },
    flower_id: {
        type: String,
        required: "Flower Id is required",
    },
    timer: {
        type: String,
        required: "Timer is required",
    },
    status: {
        type: String,
        required: "Status is required",
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Favourites', favouritesmodel);