const mongoose = require('mongoose');

const articlesmodel = mongoose.Schema({
    content_article: {
        type: String,
        required: "Content Article is required",
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Articles', articlesmodel);