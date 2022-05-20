const mongoose = require('mongoose');

const triviamodel = mongoose.Schema({
    content_trivia: {
        type: String,
        required: "Content Trivia is required",
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Trivia', triviamodel);