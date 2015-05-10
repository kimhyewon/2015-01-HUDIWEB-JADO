module.exports = function(grunt) {
	// Time how long tasks take. Can help when optimizing build times
	require("time-grunt")(grunt);

	// Load grunt tasks automatically
	require("load-grunt-tasks")(grunt);

	var config = {
		app: "webapps/",
		
		// 배포용
		dist : "webapps/dist"
	};

	// Project configuration.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),
		clean: {
			build: {
				src: ['webapps/css/jado_con.css', 'webapps/css/jado.css',
				'webapps/js/jado_lib_con.js', 'webapps/js/jado_lib.js',
				'webapps/js/jado_con.js', 'webapps/js/jado.js']
			}
		},
		// The actual grunt server settings
		connect: {
			options: {
				port: 9000,
				livereload: 35729,
				hostname: "localhost"
			},
			proxies: [{
				context: "/",
				host: "localhost",
				port: 8080
			}],
			livereload: {
				options: {
					open: true,
					base:[
						//".tmp",
						"webapps/"
						],
					middleware: function(connect,options) {
						var middlewares = [];

						if (!Array.isArray(options.base)) {
							options.base = [options.base];
						}

					// Setup the proxy
					middlewares.push(require("grunt-connect-proxy/lib/utils").proxyRequest);

					// Serve static files
					options.base.forEach(function(base) {
						middlewares.push(connect.static(base));
					});

					return middlewares;
				}
			}
		}
	},

	// concat 설정
	concat:{
		basic_and_extras: {
			files: {
				'webapps/js/jado_lib_con.js': ['webapps/js/lib/*.js'],
				'webapps/js/jado_con.js': ['webapps/js/jadoJS/*.js'],
				'webapps/css/jado_con.css': ['webapps/css/lib/reset.css', 'webapps/css/lib/grid.css', 'webapps/css/jadoCSS/*.css']
				// concat 결과 파일: concat 타겟 설정(앞에서부터 순서대로
				// 합쳐진다.)
			}
		}
	},
	// uglify 설정
	uglify: {
		options: {
		},
		my_target: {
			files: {
				'webapps/js/jado_lib.js': ['webapps/js/jado_lib_con.js'],
				'webapps/js/jado.js': ['webapps/js/jado_con.js']
			}
		}
	},
	// css uglify 설정
	cssmin: {
		dist: {
			src: 'webapps/css/jado_con.css',
			dest: 'webapps/css/jado.css'
		}
	},
	watch: {
		scripts: {
			files: ['webapps/js/lib/*.js', 'webapps/js/jadoJS/*.js',
					'webapps/css/lib/*.css', 'webapps/css/jadoCSS/*.css'],
			tasks: ['clean', 'concat', 'uglify', 'cssmin'],
			options: {
				spawn: false,
				livereload: true
	    	},
			livereload: {
				options: {
					livereload: "<%= connect.options.livereload %>"
				},
				files: [
					"webapps/{,*/}*.html",
					"webapps/css/{,*/}*.css",
					"webapps/img/{,*/}*"
				]
			}
		},
	}
});

// Load the plugins
// 아래 6줄은 상단에 작성되어 있는 우측 명령어가 대신하게 됨 -> require("load-grunt-tasks")(grunt);
// grunt.loadNpmTasks('grunt-contrib-clean');
// grunt.loadNpmTasks('grunt-contrib-concat');
// grunt.loadNpmTasks('grunt-yui-compressor');
// grunt.loadNpmTasks('grunt-contrib-uglify');
// grunt.loadNpmTasks('grunt-contrib-watch');
// grunt.loadNpmTasks('grunt-contrib-watch');

// Default task(s).
 // grunt 명령어로 실행할 작업
grunt.registerTask('default', ['clean', 'concat', 'uglify', 'cssmin']);

grunt.registerTask("serve", "start the server and preview your app, --allow-remote for remote access", function (target) {
	if (grunt.option("allow-remote")) {
		grunt.config.set("connect.options.hostname", "0.0.0.0");
	}
	if (target === "dist") {
		return grunt.task.run(["build", "connect:dist:keepalive"]);
	}

    grunt.task.run([
			"clean",
			"concat", 
			"uglify", 
			"cssmin",
			"configureProxies:server",
			"connect:livereload",
			"watch"
    	]);
	});
};
