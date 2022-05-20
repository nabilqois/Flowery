const mongoose = require('mongoose');

const flowermodel = mongoose.Schema({
    global_name: {
        type: String,
        required: "Global Name is required",
    },
    scientific_name: {
        type: String,
        required: "Scientific Name is required",
    },  
    local_name: {
        type: String,
        required: "Local Name is required",
    },
    reference: {
        type: String,
        required: "Reference is required",
    },
    images: {
        type: String,
        required: "Images is required",
    } 
}, {
    timestamps: true
});

module.exports = mongoose.model('Flower', flowermodel);