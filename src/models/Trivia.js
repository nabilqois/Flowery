const mongoose = require('mongoose');

const triviamodel = mongoose.Schema({
    flower_id: {
        type: String,
        required: "Flower Id is required",
    },
    content_trivia: {
        type: String,
        required: "Content Trivia is required",
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Trivia', triviamodel);