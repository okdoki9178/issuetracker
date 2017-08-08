module.exports = {
    entry: './src/front/main.js',
    output: {
        path: __dirname+'/src/main/resources/static/js/',
        filename: 'bundle.js'
    },
    module: {

        loaders: [
            {
                test: /\.vue$/,
                loader: 'vue-loader',
                options: {
                    loaders: {

                    }
                    // other vue-loader options go here
                }
            },
            {
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /(node_modules|bower_components)/,
                query: {
                    presets: ['es2015']
                }
            }
        ]
    }
};