const mongoose = require('mongoose');

const tutorialmodel = mongoose.Schema({
    flower_id: {
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Flower',
        required: "Flower Id is required",
    },
    plant: [{
        type: String,
        required: "How to Plant Id is required",
    }],  
    take_care: [{
        type: String,
        required: "How to Take Care Id is required",
    }]
}, {
    timestamps: true
});

module.exports = mongoose.model('Tutorial', tutorialmodel);