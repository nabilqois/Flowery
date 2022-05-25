const mongoose = require('mongoose');

const articlesmodel = mongoose.Schema({
    title_article: {
        type: String,
        required: "Title of the Article is required",
    },
    content_article: {
        type: String,
        required: "Content of the Article is required",
    }
}, {
    timestamps: true
});

module.exports = mongoose.model('Articles', articlesmodel);