const mongoose = require('mongoose');

const tutorialmodel = mongoose.Schema({
    flower_id: {
        type: String,
        required: "Flower Id is required",
    },
    plant: [{
        type: String,
    }],  
    take_care: [{
        type: String,
    }]
}, {
    timestamps: true
});

module.exports = mongoose.model('Tutorial', tutorialmodel);