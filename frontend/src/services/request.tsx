import { Modal } from "antd";
import { configConsumerProps } from "antd/lib/config-provider";
import axios from "axios";
import i18next from 'i18next';
import { ErrorComp } from './exception';

const request = axios.create({
  timeout: 5 * 1000,
  baseURL: process.env.NODE_ENV === "development" ? "/api" : "",
  headers: {
    "Content-Type": "application/json",
  },
});

request.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers = {
      Authorization: token,
      ...config.headers,
    };
  }
  return config;
});

request.interceptors.response.use(
  (response) => {
    const { status, config, data } = response;

    // console.log("response====", response);
    const statusCategory = Math.floor(status / 100);
    if (statusCategory === 2) {
      if (data && data.data) {
        return Promise.resolve(data.data);
      }
      return Promise.resolve(data);
    }
    return Promise.resolve(response);
  },
  (error) => {
    // console.log("error====", error);
    let { message, config, code } = error;
    if (error.response) {
      const { status, data } = error.response;
      const messageKey = 'request.error.' + status;
      const localizedMessage = i18next.t(messageKey);
      if (localizedMessage !== messageKey) {
        message = localizedMessage;
      }
      code = status;
      if (data) {
        config.data = typeof data === 'string' ? data : JSON.stringify(data);
      }
    }
    showErrorModal(message, config, code);
    return Promise.reject(error);
  }
);

function showErrorModal(message: string, config: object, code?: number) {
  Modal.warning({
    title: i18next.t('misc.error'),
    content: <ErrorComp content={message} options={config} code={code} />,
    okText: i18next.t('misc.close'),
    width: 560,
  });
}

export default request;