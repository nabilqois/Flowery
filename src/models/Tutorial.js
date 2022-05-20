const mongoose = require('mongoose');

const tutorialmodel = mongoose.Schema({
    flower_id: {
        type: String,
        required: "Flower Id is required",
    },
    plant: {
        type: String,
        required: "Plant Name is required",
    },  
    take_care: {
        type: String,
        required: "Take Care is required",
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Tutorial', tutorialmodel);