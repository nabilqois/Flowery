const mongoose = require('mongoose');

const flowermodel = mongoose.Schema({
    global_name: {
        type: String,
        required: "Global Name is required",
    }, 
    local_name: {
        type: String,
        required: "Local Name is required",
    }, 
    scientific_name: {
        type: String,
        required: "Scientific Name is required",
    }, 
    how_to_tend: {
        type: String,
        required: "How to Tend Name is required",
    }, 
    other_usage: {
        type: String,
        required: "Other Usage is required",
    },
    reference: {
        type: String,
        required: "Reference is required",
    } 
}, {
    timestamps: true
});

module.exports = mongoose.model('Flower', flowermodel);