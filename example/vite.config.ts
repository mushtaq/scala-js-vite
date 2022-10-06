import {defineConfig, PluginOption, UserConfig} from 'vite'

export default defineConfig(({command, mode}): UserConfig => {
    return {
        plugins: command === "build" ? [htmlPlugin()] : [],
        build: {
            target: "esnext"
        },
    };
})

const htmlPlugin = (): PluginOption => {
    return {
        name: "html-transform",
        transform: (file, fileName) => {
            return fileName.endsWith('.html') ? file.replace("example-fastopt", "example-opt") : file
        },
        enforce: 'pre'
    };
};
