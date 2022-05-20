const mongoose = require('mongoose');

const favouritesmodel = mongoose.Schema({
    flower_id: {
        type: String,
        required: "Flower Id is required",
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Favourites', favouritesmodel);