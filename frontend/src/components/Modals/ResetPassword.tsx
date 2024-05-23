import React from "react";

type ResetPasswordProps = {};

const ResetPassword: React.FC<ResetPasswordProps> = () => {
  return (
    <div>
      <form className="space-y-6 px-6 py-5">
        <h3 className="text-x; font-medium text-white">Reset Password</h3>
        <div className="text-sm font-medium text-gray-300">
          Forgotten your password? Enter your e-mail address below, and
          we&apos;ll send you an e-mail allowillg you to reset it.
        </div>
        <div>
          <label
            htmlFor="email"
            className="block text-sm font-medium text-white mb-2 text-gray"
          >
            Email
          </label>
          <input
            type="email"
            id="email"
            className="border-2 outline-none sm:text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white"
            placeholder="name@company.com"
          />
        </div>
        <button
          type="submit"
          className="w-full text-white focus:ring-blue-300 font-medium rounder-lg text-sm px-5 py-2.5 text-center bg-brand-orange hover:bg-brand-orange-s"
        >
          Reset Password
        </button>
      </form>
    </div>
  );
};

export default ResetPassword;
